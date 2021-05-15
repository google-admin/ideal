// Autogenerated from development/types/base_value_printer.i

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
import ideal.machine.channels.string_writer;

public class base_value_printer extends debuggable implements value_printer {
  private static final boolean OMIT_DEFAULT_FLAVOR = false;
  private final principal_type library_elements_type;
  public base_value_printer(final principal_type library_elements_type) {
    this.library_elements_type = library_elements_type;
  }
  public @Override string print_value(final abstract_value the_value) {
    return this.print_type(the_value.type_bound());
  }
  public string print_type(final type the_type) {
    if (the_type instanceof flavored_type) {
      final type_flavor flavor = ((flavored_type) the_type).get_flavor();
      final string principal_name = this.print_type(((flavored_type) the_type).principal());
      if (base_value_printer.OMIT_DEFAULT_FLAVOR && flavor == ((flavored_type) the_type).principal().get_flavor_profile().default_flavor()) {
        return principal_name;
      } else {
        return ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(flavor, new base_string(" ")), principal_name);
      }
    } else {
      final principal_type principal = (principal_type) the_type;
      if (type_utilities.is_union(principal)) {
        return this.print_union_type(principal);
      }
      final string principal_name = (principal.get_parent() == this.library_elements_type) ? principal.short_name().to_string() : this.print_hierarchical_name(principal);
      if (principal instanceof parametrized_type) {
        return ideal.machine.elements.runtime_util.concatenate(principal_name, this.print_parameters(((parametrized_type) principal)));
      } else {
        return principal_name;
      }
    }
  }
  private string print_hierarchical_name(final principal_type the_type) {
    final immutable_list<simple_name> full_names = type_utilities.get_full_names(the_type);
    if (full_names.is_empty()) {
      return the_type.to_string();
    } else {
      final string_writer the_writer = new string_writer();
      boolean first = true;
      {
        final readonly_list<simple_name> name_list = full_names;
        for (int name_index = 0; name_index < name_list.size(); name_index += 1) {
          final simple_name name = name_list.get(name_index);
          if (first) {
            first = false;
          } else {
            the_writer.write('.');
          }
          the_writer.write_all(name.to_string());
        }
      }
      return the_writer.elements();
    }
  }
  private string print_union_type(final principal_type the_type) {
    final immutable_list<abstract_value> parameters = type_utilities.get_union_parameters(the_type);
    assert parameters.size() == 2;
    return ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(this.print_value(parameters.get(0)), new base_string(" or ")), this.print_value(parameters.get(1)));
  }
  private string print_parameters(final parametrized_type the_type) {
    final string_writer the_writer = new string_writer();
    final immutable_list<abstract_value> parameters = the_type.get_parameters().internal_access();
    the_writer.write('[');
    boolean first = true;
    {
      final readonly_list<abstract_value> parameter_list = parameters;
      for (int parameter_index = 0; parameter_index < parameter_list.size(); parameter_index += 1) {
        final abstract_value parameter = parameter_list.get(parameter_index);
        if (first) {
          first = false;
        } else {
          the_writer.write_all(new base_string(", "));
        }
        the_writer.write_all(this.print_value(parameter));
      }
    }
    the_writer.write(']');
    return the_writer.elements();
  }
}
