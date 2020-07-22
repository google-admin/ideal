// Autogenerated from development/texts/start_element.i

package ideal.development.texts;

import ideal.library.elements.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.runtime.texts.*;

public class start_element extends debuggable implements text_event {
  private final element_id id;
  public start_element(final element_id id) {
    this.id = id;
  }
  public element_id get_id() {
    return this.id;
  }
  public @Override string to_string() {
    return new base_string(new base_string("<"), this.id.to_string(), new base_string(">"));
  }
}
