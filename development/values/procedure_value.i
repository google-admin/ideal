-- Copyright 2014-2025 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://theideal.org/license/

interface procedure_value {
  extends abstract_value, value_wrapper;

  action_name name;
  -- This is needed to resolve abstract_value/value_wrapper ambiguity...
  override type type_bound;
  declaration or null get_declaration;
  boolean has_this_argument;
  boolean supports_parameters(action_parameters parameters, action_context context) pure;
  action bind_this_action(action from, origin the_origin);
  procedure_value bind_this(entity_wrapper this_argument);
  analysis_result bind_parameters(action_parameters params, action_context context,
      origin the_origin);
  entity_wrapper execute(entity_wrapper this_argument, readonly list[entity_wrapper] args,
      execution_context the_execution_context);
}
