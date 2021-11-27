// Autogenerated from development/types/parametrizable_state.i

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
import ideal.development.modifiers.*;

import javax.annotation.Nullable;
import ideal.machine.annotations.dont_display;

public class parametrizable_state extends debuggable {
  private final master_type master;
  private final @dont_display dictionary<type_parameters, parametrized_type> parametrized_types;
  private @Nullable parametrized_type primary_type;
  private @Nullable immutable_list<variance_modifier> variances;
  public parametrizable_state(final master_type master) {
    this.master = master;
    this.parametrized_types = new hash_dictionary<type_parameters, parametrized_type>();
  }
  public master_type get_master() {
    return this.master;
  }
  public type bind_parameters(final type_parameters parameters) {
    @Nullable parametrized_type result = this.parametrized_types.get(parameters);
    if (result == null) {
      result = this.make_parametrized();
      this.parametrized_types.put(parameters, result);
      result.set_parameters(parameters);
      final ideal.library.graphs.graph<principal_type, origin> the_type_graph = this.master.declaration_context().type_graph();
      if (this.primary_type != null) {
        assert !the_type_graph.introduces_cycle(result, this.primary_type);
        the_type_graph.add_edge(result, this.primary_type, type_utilities.PRIMARY_TYPE_ORIGIN);
      } else {
        assert this.is_special();
      }
    }
    return result;
  }
  private boolean is_special() {
    return this.master.get_kind() == type_kinds.procedure_kind;
  }
  private parametrized_type make_parametrized() {
    return new parametrized_type(this.master);
  }
  public @Nullable parametrized_type get_primary() {
    return this.primary_type;
  }
  public parametrized_type make_primary() {
    assert this.primary_type == null;
    final parametrized_type result = this.make_parametrized();
    this.primary_type = result;
    return result;
  }
  public @Nullable parametrized_type lookup_parametrized(final type_parameters parameters) {
    return this.parametrized_types.get(parameters);
  }
  public void bind_parametrized(final parametrized_type parametrized, final type_parameters parameters) {
    assert parametrized.get_master() == this.master;
    assert !parametrized.parameters_defined();
    assert this.is_special() || this.primary_type == parametrized;
    if (this.parametrized_types.contains_key(parameters)) {
      utilities.panic(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(new base_string("Already defined param type "), parametrized), new base_string(" for ")), parameters));
    }
    parametrized.set_parameters(parameters);
    this.parametrized_types.put(parameters, parametrized);
  }
  public void set_variances(final readonly_list<variance_modifier> variances) {
    assert this.variances == null;
    this.variances = variances.frozen_copy();
  }
  public variance_modifier get_variance(final Integer parameter_index) {
    if (this.variances != null) {
      if (parameter_index < this.variances.size()) {
        final immutable_list<variance_modifier> variance_list = this.variances;
        return variance_list.get(parameter_index);
      }
    }
    return variance_modifier.invariant_modifier;
  }
  public @Override string to_string() {
    return utilities.describe(this, this.master);
  }
}
