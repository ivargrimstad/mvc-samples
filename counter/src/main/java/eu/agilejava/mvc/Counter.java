package eu.agilejava.mvc;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Singleton
public class Counter {

   private static final Logger LOGGER = Logger.getLogger(CountObserver.class.getName());

   private final AtomicLong counter = new AtomicLong();

   /**
    * Retrieves the next number.
    *
    * @return the next count
    */
   public long next() {
      return counter.incrementAndGet();
   }

   @PostConstruct
   private void init() {
      LOGGER.config(() -> this.getClass().getSimpleName() + " created");
   }
}
