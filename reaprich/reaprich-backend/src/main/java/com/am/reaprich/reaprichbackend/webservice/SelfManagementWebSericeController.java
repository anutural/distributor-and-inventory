package com.am.reaprich.reaprichbackend.webservice;


import com.am.reaprich.reaprichbackend.business.pojo.uermanagement.*;
import com.am.reaprich.reaprichbackend.business.service.usermanagement.ActorService;
import com.am.reaprich.reaprichbackend.business.service.auth.AuthenticationService;
import com.am.reaprich.reaprichbackend.business.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST})
@RequestMapping("/v1/self")
public class SelfManagementWebSericeController {
    private static final Logger logger = LogManager.getLogger(SelfManagementWebSericeController.class);
    private final ActorService actorService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    @GetMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutletDetailResponse> outlet() {
        final String PQMN  = "outlet";
        logger.info(PQMN + " - Start");
        try {
            String userEmail = getUserNameFromAuthHeader();
            return ResponseEntity.ok(this.actorService.getOutletDetailByUserEamil(userEmail));
        }
        catch (IllegalArgumentException ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getOutletDetailRespForBadRequestError(ex);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getOutletDetailRespForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @GetMapping("/actor/td")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TDDetailResponse> td() {
        final String PQMN  = "td";
        logger.info(PQMN + " - Start");
        try {
            String userEmail = getUserNameFromAuthHeader();
            return ResponseEntity.ok(this.actorService.getTDDetailByUserEamil(userEmail));
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getTDDetailRespForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/actor/outlet")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OutletDetailResponse> updateOutlet(@RequestBody UpdateOutletDetailRequest updateOutletDetailRequest) {
        final String PQMN  = "updateOutlet";
        logger.info(PQMN + " - Start");
        try {
            String userEmail = getUserNameFromAuthHeader();
            OutletDetailResponse resp = this.actorService.updateOutletDetail(userEmail, updateOutletDetailRequest);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        catch (IllegalArgumentException ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return getOutletDetailRespForBadRequestError(ex);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getOutletDetailRespForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/actor/td")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TDDetailResponse> updateTD(@RequestBody UpdateTDDetailRequest updateTDDetailRequest) {
        final String PQMN  = "updateTD";
        logger.info(PQMN + " - Start");
        try {
            String userEmail = getUserNameFromAuthHeader();
            TDDetailResponse resp = this.actorService.updateTDDetail(userEmail, updateTDDetailRequest);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  getTDDetailRespForInternalServerError(ex);
        } finally {
            logger.info(PQMN + " - End");
        }
    }

    @PutMapping("/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest restPasswordRequest) {
        final String PQMN  = "resetPassword";
        logger.info(PQMN + " - Start");
        try {
            this.authenticationService.resetPassword(restPasswordRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }
        catch (IllegalArgumentException ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch (Exception ex) {
            logger.error(ex.toString());
            Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(x -> logger.error(x.toString()));
            return  ResponseEntity.internalServerError().body(ex.getMessage());
        } finally {
            logger.info(PQMN + " - End");
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
