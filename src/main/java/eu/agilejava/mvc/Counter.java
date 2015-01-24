package eu.agilejava.mvc;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Singleton
public class Counter {

   @Inject
   private Logger logger;

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
      logger.config(() -> this.getClass().getSimpleName() + " created");
   }
}
