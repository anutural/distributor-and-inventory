package com.am.reaprich.reaprichbackend.webservice;


import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.*;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ActorService;
import com.am.reaprich.reaprichbackend.business.service.auth.AuthenticationService;
import com.am.reaprich.reaprichbackend.business.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/self")
public class SelfManagementWebSericeController {
    private final ActorService actorService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    @GetMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutletDetailResponse> outlet() {
        try {
            String userEmail = getUserNameFromAuthHeader();
            return ResponseEntity.ok(this.actorService.getOutletDetailByUserEamil(userEmail));
        }
        catch (IllegalArgumentException ex) {
            return getOutletDetailRespForBadRequestError(ex);
        }
        catch (Exception ex) {
            return  getOutletDetailRespForInternalServerError(ex);
        }
    }

    @GetMapping("/actor/td")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity td() {
        try {
            String userEmail = getUserNameFromAuthHeader();
            return ResponseEntity.ok(this.actorService.getTDDetailByUserEamil(userEmail));
        }
        catch (Exception ex) {
            return  getTDDetailRespForInternalServerError(ex);
        }
    }

    @PutMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutletDetailResponse> updateOutlet(@RequestBody UpdateOutletDetailRequest updateOutletDetailRequest) {
        try {
            String userEmail = getUserNameFromAuthHeader();
            OutletDetailResponse resp = this.actorService.updateOutletDetail(userEmail, updateOutletDetailRequest);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        catch (IllegalArgumentException ex) {
            return getOutletDetailRespForBadRequestError(ex);
        }
        catch (Exception ex) {
            return  getOutletDetailRespForInternalServerError(ex);
        }
    }

    @PutMapping("/actor/td")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TDDetailResponse> updateTD(@RequestBody UpdateTDDetailRequest updateTDDetailRequest) {
        try {
            String userEmail = getUserNameFromAuthHeader();
            TDDetailResponse resp = this.actorService.updateTDDetail(userEmail, updateTDDetailRequest);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        catch (Exception ex) {
            return  getTDDetailRespForInternalServerError(ex);
        }
    }

    @PutMapping("/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest restPasswordRequest) {
        try {
            this.authenticationService.resetPassword(restPasswordRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch (Exception ex) {
            return  ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }


    private ResponseEntity<OutletDetailResponse> getOutletDetailRespForInternalServerError(Exception ex) {
        return ResponseEntity.internalServerError().body(
                OutletDetailResponse.builder()
                        .error(ex.getMessage())
                        .build()
        );
    }
    private ResponseEntity<OutletDetailResponse> getOutletDetailRespForBadRequestError(Exception ex) {
        return ResponseEntity.badRequest().body(
                OutletDetailResponse.builder()
                        .error(ex.getMessage())
                        .build()
        );
    }

    private ResponseEntity<TDDetailResponse> getTDDetailRespForInternalServerError(Exception ex) {
        if (ex.getClass() == IllegalArgumentException.class)
        {
            return ResponseEntity.badRequest().body(TDDetailResponse.builder()
                    .error(ex.getMessage())
                    .build());
        }
        return ResponseEntity.internalServerError().body(
                TDDetailResponse.builder()
                        .error("Something went wrong")
                        .build()
        );
    }

    private String getUserNameFromAuthHeader() throws Exception{
        final String authHeader = this.request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new Exception("Invalid Token passed");
        }
        refreshToken = authHeader.substring(7);
        userEmail = this.jwtService.extractUserName(refreshToken);
        return userEmail;
    }
}
