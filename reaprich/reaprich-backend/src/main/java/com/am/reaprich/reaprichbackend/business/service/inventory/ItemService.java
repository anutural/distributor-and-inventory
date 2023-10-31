package com.am.reaprich.reaprichbackend.business.service.inventory;

import com.am.reaprich.reaprichbackend.business.pojo.inventory.*;
import com.am.reaprich.reaprichbackend.data.entities.inventory.Item;
import com.am.reaprich.reaprichbackend.data.entities.inventory.ItemOffer;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Category;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.PackingType;
import com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider.Subcategory;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.ItemOfferRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.ItemRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.inventoryprovider.CategoryRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.inventoryprovider.PackingTypeRepository;
import com.am.reaprich.reaprichbackend.data.repositories.inventory.inventoryprovider.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PackingTypeRepository packingTypeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private ItemOfferRepository itemOfferRepository;

    public ItemResponse getItem(String id) throws Exception{
        Optional<Item> optionalItem = this.itemRepository.findById(id);

        if (optionalItem.isEmpty()) {
            throw new IllegalArgumentException("Item with the specified ID doesn't exist");
        }
        return ItemResponse.builder().item(optionalItem.get()).build();
    }

    public ItemCollectionResponse getAllItems(GetAllItemRequest getAllItemRequest) throws Exception {
        Iterable<Item> allItems = this.itemRepository.findAll();
        return ItemCollectionResponse.builder().items(new ItemFilter().doFilter(this.itemRepository, getAllItemRequest)).build();
    }

    private Category getCategoryById(String categoryID) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryID);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("Category with specified id doesn't exist");
        }
        return optionalCategory.get();
    }

    private Subcategory getSubcategoryById(String subcategoryID) {
        Optional<Subcategory> optionalSubcategory = this.subcategoryRepository.findById(subcategoryID);
        if (optionalSubcategory.isEmpty()) {
            throw new IllegalArgumentException("Subcategory with specified id doesn't exist");
        }
        return optionalSubcategory.get();
    }

    private PackingType getPackingTypeById(String packingTypeId) {
        Optional<PackingType> optionalPackingType = this.packingTypeRepository.findById(packingTypeId);
        if (optionalPackingType.isEmpty()) {
            throw new IllegalArgumentException("packing type with specified id doesn't exist");
        }
        return optionalPackingType.get();
    }

    public void addItem(Item item) {
        if (!this.itemRepository.findById(item.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item with the same ID is already present");
        }
        item.setCategory(getCategoryById(item.getCategory().getId()));
        item.setSubcategory(getSubcategoryById(item.getSubcategory().getId()));
        item.setPackingType(getPackingTypeById(item.getPackingType().getId()));
        this.itemRepository.save(item);
    }

    public void updateItemDetail(Item item) {
        if (this.itemRepository.findById(item.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item with the specified ID does not exist");
        }
        this.itemRepository.save(item);
    }

    public void addPackingType(PackingType packingType) {
        if (!this.packingTypeRepository.findById(packingType.getId()).isEmpty()) {
            throw new IllegalArgumentException("Packing Type with the same ID is already present");
        }
        this.packingTypeRepository.save(packingType);
    }

    public void addCategory(Category category) {
        if (!this.categoryRepository.findById(category.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item category with the same ID is already present");
        }
        this.categoryRepository.save(category);
    }

    public void addSubcategory(Subcategory subcategory) {
        if (!this.subcategoryRepository.findById(subcategory.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item subcategory with the same ID is already present");
        }
        subcategory.setCategory(getCategoryById(subcategory.getCategory().getId()));
        this.subcategoryRepository.save(subcategory);
    }

    public void addItemOffer(ItemOffer itemOffer) {
        if (!this.itemRepository.findById(itemOffer.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item offer with the same ID is already present");
        }

        this.itemOfferRepository.save(itemOffer);
    }

    public void updateItemOffer(ItemOffer itemOffer) {
        if (this.itemOfferRepository.findById(itemOffer.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item offer with the specified ID does not exist");
        }
        this.itemOfferRepository.save(itemOffer);
    }

    public ItemOfferCollectionResponse getAllItemOffers() {
        return ItemOfferCollectionResponse
                .builder()
                .itemOffers(
                        StreamSupport
                                .stream(this.itemOfferRepository.findAll().spliterator(), false)
                                .collect(Collectors.toList()))
                .build();
    }

    public ItemOfferCollectionResponse getItemOffersOnItem(String itemID) {
        return ItemOfferCollectionResponse
                .builder()
                .itemOffers(
                        StreamSupport
                                .stream(this.itemOfferRepository.findByItem(itemID).spliterator(), false)
                                .collect(Collectors.toList()))
                .build();
    }
}

class ItemFilter {
    public List<Item> doFilter (ItemRepository itemRepository, GetAllItemRequest getAllItemRequest)  throws Exception {
        try {
            if (getAllItemRequest.getItemFilterBy() == ItemFilterBy.ID) {
                StreamSupport.stream(itemRepository.findAllById(new Iterable<String>() {
                    @Override
                    public Iterator<String> iterator() {
                        return Arrays.asList(getAllItemRequest.getFilter().split(",")).iterator();
                    }
                }).spliterator(), false).collect(Collectors.toList());
            }

            List<Item> allItems = StreamSupport
                    .stream(itemRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());

            switch (getAllItemRequest.getItemFilterBy()) {
                case NONE:
                    return allItems;
                case CATEGORY:
                    return allItems.stream()
                            .filter(item -> item.getCategory().getCategory() == getAllItemRequest.getFilter())
                            .collect(Collectors.toList());
                case SUBCATEGORY:
                    return allItems.stream()
                            .filter(item -> item.getSubcategory().getSubcategory() == getAllItemRequest.getFilter())
                            .collect(Collectors.toList());
                case PACKING_TYPE:
                    return allItems.stream()
                            .filter(item -> item.getPackingType().getPackingType() == getAllItemRequest.getFilter())
                            .collect(Collectors.toList());
                case NAME_CHARS:
                    return allItems.stream()
                            .filter(item -> item.getName().contains(getAllItemRequest.getFilter()))
                            .collect(Collectors.toList());
                default:
                    double min = Double.parseDouble(getAllItemRequest.getFilter().split("-")[0]);
                    double max = Double.parseDouble(getAllItemRequest.getFilter().split("-")[1]);
                    return allItems.stream()
                            .filter(item -> item.getReatilPrice() >= min && item.getReatilPrice() <= max)
                            .collect(Collectors.toList());
            }
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Item filter failed for FilterBy:"
                    + getAllItemRequest.getItemFilterBy().name()
                    + " and Filter:" + getAllItemRequest.getFilter());
        }
    }


}
