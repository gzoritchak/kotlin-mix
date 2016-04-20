package org.mixit.kotlin.service;

import org.mixit.kotlin.domain.Hotel;
import org.mixit.kotlin.domain.HotelSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component("hotelService")
@Transactional
class HotelServiceImpl implements HotelService {

    private final EntityManager em;

    @Autowired
    public HotelServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Hotel> getHotels() {
        return em.createQuery("select h from Hotel h join fetch h.city").getResultList();
    }

    @Override
    public List<HotelSummary> getHotelSummaries() {
        return em.createQuery(
                "select new org.mixit.kotlin.domain.HotelSummary(h.city, h.name, avg(r.rating)) " +
                        "from Hotel h left outer join h.reviews r  group by h").getResultList();
    }

    @Override
    public void changeName(@NotNull Long id, @NotNull String name) {
        em.find(Hotel.class, id).setName(name);
    }


}
