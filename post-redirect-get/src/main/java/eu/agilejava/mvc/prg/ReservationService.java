/*
 * The MIT License
 *
 * Copyright 2015 Ivar Grimstad (ivar.grimstad@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.agilejava.mvc.prg;

import eu.agilejava.mvc.domain.Reservation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Named
@Singleton
public class ReservationService {
    
    private final Set<Reservation> orders;

    public ReservationService() {
        this.orders = new HashSet<>();
    }
    
    public Reservation save(@NotNull Reservation order) {
        
        if(order.getId() == null || order.getId().isEmpty() ) {
            order.setId(UUID.randomUUID().toString());
        }
        orders.remove(order);
        orders.add(order);
        
        return order;
    }
    
    public List<Reservation> findAll() {
        return orders.stream()
                     .collect(toList());
    }
    
    public Reservation findById(final String id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id)).findFirst().orElse(new Reservation());
    }
    
    public void remove(final String id) {
        orders.remove(findById(id));
    }
    
    @PostConstruct
    private void init() {
        
    }
    
}
