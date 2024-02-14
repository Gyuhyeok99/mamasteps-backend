package inhagdsc.mamasteps.map.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inhagdsc.mamasteps.common.ApiResponse;
import inhagdsc.mamasteps.map.dto.RouteDto;
import inhagdsc.mamasteps.map.dto.RouteRequestDto;
import inhagdsc.mamasteps.map.dto.RouteRequestProfileDto;
import inhagdsc.mamasteps.map.service.RoutesService;
import inhagdsc.mamasteps.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static inhagdsc.mamasteps.common.code.status.ErrorStatus.*;
import static inhagdsc.mamasteps.common.code.status.SuccessStatus.*;

@RestController
@RequestMapping("/api/v1/routes")
public class RoutesController {

    private final RoutesService routesService;

    @Autowired
    public RoutesController(RoutesService routesService) {
        this.routesService = routesService;
    }

    @GetMapping("/createProfile")
    public ApiResponse<RouteRequestProfileDto> createRequestProfile(@AuthenticationPrincipal User user) {
        return ApiResponse.onSuccess(CREATED, routesService.createRequestProfile(user));
    }

    @GetMapping("/getRequestProfile")
    public ApiResponse<RouteRequestProfileDto> getRequestProfile(@AuthenticationPrincipal User user) {
        RouteRequestProfileDto response = routesService.getRequestProfile(user.getId());
        return ApiResponse.onSuccess(OK, response);
    }

    @PostMapping("/editProfile")
    public ApiResponse<RouteRequestProfileDto> editProfile(@AuthenticationPrincipal User user, @RequestBody RouteRequestProfileDto routesProfileDto) {
        try {
            return ApiResponse.onSuccess(OK, routesService.editRequestProfile(user.getId(), routesProfileDto));
        } catch (Exception e) {
            return ApiResponse.onFailure(FORBIDDEN.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping("/saveRoute")
    public ApiResponse<Void> saveRoute(@AuthenticationPrincipal User user, @RequestBody RouteDto routeDto) {
        try {
            routesService.saveRoute(user.getId(), routeDto);
            return ApiResponse.onSuccess(OK, null);
        } catch (Exception e) {
            return ApiResponse.onFailure(FORBIDDEN.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/getRoutes")
    public ApiResponse<List<RouteDto>> getRoutes(@AuthenticationPrincipal User user) {
        List<RouteDto> response = routesService.getRoutes(user.getId());
        return ApiResponse.onSuccess(OK, response);
    }

    @PostMapping("/editRouteName/{routeId}")
    public ApiResponse<Void> editRoute(@AuthenticationPrincipal User user, @PathVariable Long routeId, @RequestBody String name) {
        try {
            routesService.editRouteName(user.getId(), routeId, name);
            return ApiResponse.onSuccess(OK, null);
        } catch (Exception e) {
            return ApiResponse.onFailure(FORBIDDEN.getCode(), e.getMessage(), null);
        }
    }

    @DeleteMapping("/deleteRoute/{routeId}")
    public ApiResponse<Void> deleteRoute(@AuthenticationPrincipal User user, @PathVariable Long routeId) {
        try {
            routesService.deleteRoute(user.getId(), routeId);
            return ApiResponse.onSuccess(OK, null);
        } catch (Exception e) {
            return ApiResponse.onFailure(FORBIDDEN.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/computeRoutes")
    public ApiResponse<List<RouteDto>> computeRoutes(@AuthenticationPrincipal User user) throws IOException {
        try {
            List<RouteDto> response = routesService.computeRoutes(user.getId());
            return ApiResponse.onSuccess(OK, response);
        } catch (Exception e) {
            return ApiResponse.onFailure(FORBIDDEN.getCode(), e.getMessage(), null);
        }
    }
}