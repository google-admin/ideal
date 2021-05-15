// Autogenerated from development/types/value_printer.i

package ideal.development.types;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.flavors.*;
import ideal.development.declarations.*;
import ideal.development.kinds.*;

public interface value_printer extends data, readonly_value_printer, writeonly_value_printer {
  string print_value(abstract_value the_value);
}
