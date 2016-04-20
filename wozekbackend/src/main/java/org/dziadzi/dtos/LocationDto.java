package org.dziadzi.dtos;

/**
 * Created by kkuc on 2016-03-18.
 */
public class LocationDto {
    private Long id;
    private Integer y;
    private Integer x;
    private StorageDto storage;
    private ForkLiftDto forkLift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }


    public StorageDto getStorage() {
        return storage;
    }

    public void setStorage(StorageDto storage) {
        this.storage = storage;
    }

    public ForkLiftDto getForkLift() {
        return forkLift;
    }

    public void setForkLift(ForkLiftDto forkLift) {
        this.forkLift = forkLift;
    }
}
