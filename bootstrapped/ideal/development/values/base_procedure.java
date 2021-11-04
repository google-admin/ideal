// Autogenerated from development/values/base_procedure.i

package ideal.development.values;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.reflections.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.flavors.*;
import ideal.development.declarations.*;
import ideal.development.kinds.*;
import ideal.development.types.*;
import ideal.development.jumps.*;
import ideal.development.notifications.*;
import ideal.development.flags.*;

import javax.annotation.Nullable;

public abstract class base_procedure extends base_data_value implements procedure_value {
  private final action_name the_action_name;
  private @Nullable procedure_declaration the_declaration;
  public base_procedure(final action_name the_action_name, final type procedure_type) {
    super(procedure_type);
    this.the_action_name = the_action_name;
  }
  public @Override action_name name() {
    return this.the_action_name;
  }
  public @Override boolean has_this_argument() {
    return false;
  }
  public @Override procedure_value bind_this(final entity_wrapper this_argument) {
    if (this.has_this_argument()) {
      return new procedure_with_this(this, this_argument);
    } else {
      return this;
    }
  }
  public @Override action bind_this_action(final action from, final origin the_origin) {
    if (this.has_this_argument()) {
      return new procedure_with_this(this, from).to_action(the_origin);
    } else {
      return this.to_action(the_origin);
    }
  }
  public void set_declaration(final procedure_declaration the_declaration) {
    assert this.the_declaration == null;
    this.the_declaration = the_declaration;
  }
  public @Override type type_bound() {
    return this.bound;
  }
  public @Override @Nullable declaration get_declaration() {
    return this.the_declaration;
  }
  protected abstract_value return_value() {
    return common_types.get_procedure_return(this.type_bound());
  }
  protected boolean is_valid_procedure_arity(final Integer arity) {
    return common_types.is_valid_procedure_arity(this.type_bound(), arity);
  }
  protected type get_argument_type(final Integer index) {
    return common_types.get_procedure_argument(this.type_bound(), index).type_bound();
  }
  public @Override boolean is_parametrizable() {
    return true;
  }
  public @Override boolean supports_parameters(final action_parameters parameters, final action_context context) {
    final immutable_list<action> parameter_list = parameters.parameters;
    if (!this.is_valid_procedure_arity(parameter_list.size())) {
      return false;
    }
    {
      final readonly_list<Integer> index_list = parameter_list.indexes();
      for (Integer index_index = 0; index_index < index_list.size(); index_index += 1) {
        final Integer index = index_list.get(index_index);
        final action parameter = parameter_list.get(index);
        if (parameter instanceof error_signal) {
          return false;
        }
        if (!context.can_promote(parameter, this.get_argument_type(index))) {
          return false;
        }
      }
    }
    return true;
  }
  public @Override analysis_result bind_parameters(final action_parameters the_action_parameters, final action_context context, final origin the_origin) {
    final immutable_list<action> aparams = the_action_parameters.parameters;
    if (debug.DO_REDUNDANT_CHECKS) {
      if (!this.is_valid_procedure_arity(aparams.size())) {
        return new error_signal(new base_string("Arity mismatch"), the_origin);
      }
    }
    final base_list<action> promoted_params = new base_list<action>();
    {
      final readonly_list<Integer> index_list = aparams.indexes();
      for (Integer index_index = 0; index_index < index_list.size(); index_index += 1) {
        final Integer index = index_list.get(index_index);
        final action param = aparams.get(index);
        if (param instanceof error_signal) {
          return ((error_signal) param);
        }
        final type type_target = this.get_argument_type(index);
        if (context.can_promote(param, type_target)) {
          promoted_params.append(context.promote(param, type_target, the_origin));
        } else {
          return notification_utilities.cant_promote(param.result(), type_target, the_origin);
        }
      }
    }
    return new bound_procedure(this, this.return_value(), new action_parameters(promoted_params), the_origin);
  }
  public @Override abstract entity_wrapper execute(entity_wrapper this_argument, readonly_list<entity_wrapper> args, execution_context the_execution_context);
  public @Override string to_string() {
    if (this.the_declaration != null) {
      return utilities.describe(this, this.the_declaration);
    } else {
      return utilities.describe(this, this.name());
    }
  }
}
