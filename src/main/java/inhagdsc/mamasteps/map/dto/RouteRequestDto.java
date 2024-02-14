package inhagdsc.mamasteps.map.dto;

import inhagdsc.mamasteps.map.domain.LatLng;
import inhagdsc.mamasteps.map.domain.RouteRequestProfileEntity;

import java.util.List;

public class RouteRequestDto {
    int targetTime;
    LatLng origin;
    List<LatLng> startCloseIntermediates;
    List<LatLng> endCloseIntermediates;

    public RouteRequestProfileEntity toEntity() {
        RouteRequestProfileEntity routeRequestEntity = new RouteRequestProfileEntity();
        routeRequestEntity.setTargetTime(this.targetTime);
        routeRequestEntity.setOrigin(this.origin);
        routeRequestEntity.setStartCloseWaypoints(this.startCloseIntermediates);
        routeRequestEntity.setEndCloseWaypoints(this.endCloseIntermediates);
        return routeRequestEntity;
    }

    public int getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(int targetTime) {
        this.targetTime = targetTime;
    }

    public LatLng getOrigin() {
        return origin;
    }

    public void setOrigin(LatLng origin) {
        this.origin = origin;
    }

    public List<LatLng> getStartCloseIntermediates() {
        return startCloseIntermediates;
    }

    public void setStartCloseIntermediates(List<LatLng> startCloseIntermediates) {
        this.startCloseIntermediates = startCloseIntermediates;
    }

    public List<LatLng> getEndCloseIntermediates() {
        return endCloseIntermediates;
    }

    public void setEndCloseIntermediates(List<LatLng> endCloseIntermediates) {
        this.endCloseIntermediates = endCloseIntermediates;
    }
}
