package org.mixit.kotlin.service;

import org.mixit.kotlin.domain.Hotel;
import org.mixit.kotlin.domain.HotelSummary;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface HotelService {

    List<Hotel> getHotels();

    List<HotelSummary> getHotelSummaries();

    void changeName(@NotNull Long id, @NotNull String name);

}
