/*
 * Copyright 2014-2025 The Ideal Authors. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://theideal.org/license/
 */

package ideal.development.actions;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.reflections.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.types.*;
import ideal.development.values.*;
import ideal.development.declarations.*;

import javax.annotation.Nullable;

/**
 * Implements access to local, static and instance variables.
 */
public abstract class variable_action extends base_action implements abstract_value {

  public final variable_declaration the_declaration;
  public final type_flavor reference_flavor;

  protected variable_action(variable_declaration the_declaration, type_flavor reference_flavor,
      origin the_origin) {
    super(the_origin);
    this.the_declaration = the_declaration;
    this.reference_flavor = reference_flavor;
    assert the_declaration.value_type() != common_types.error_type();
  }

  public variable_action(variable_declaration the_declaration, type_flavor reference_flavor) {
    this(the_declaration, reference_flavor, the_declaration);
  }

  @Override
  public type type_bound() {
    return common_types.get_reference(reference_flavor, value_type());
  }

  @Override
  public abstract_value result() {
    return type_bound();
  }

  @Override
  public boolean has_side_effects() {
    return false;
  }

  @Override
  public variable_declaration get_declaration() {
    return the_declaration;
  }

  public type value_type() {
    return the_declaration.value_type();
  }

  @Override
  public boolean is_parametrizable() {
    return type_bound().is_parametrizable();
  }

  @Override
  public action to_action(origin pos) {
    return this;
  }

  public action_name short_name() {
    return the_declaration.short_name();
  }

  @Override
  public reference_wrapper execute(entity_wrapper from_entity,
      execution_context the_execution_context) {
    // TODO: handle jumps
    return new variable_wrapper(this, get_context(from_entity, the_execution_context));
  }

  protected abstract variable_context get_context(entity_wrapper from_entity,
      execution_context context);

  @Override
  public string to_string() {
    return utilities.describe(this, short_name());
  }
}
