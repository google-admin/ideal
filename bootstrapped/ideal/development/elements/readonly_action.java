// Autogenerated from development/elements/action.i

package ideal.development.elements;

import ideal.library.elements.*;
import ideal.library.reflections.*;

import javax.annotation.Nullable;

public interface readonly_action extends readonly_analysis_result, readonly_origin, readonly_data, any_action {
  abstract_value result();
  action bind_from(action from, origin the_origin);
  @Nullable declaration get_declaration();
}
