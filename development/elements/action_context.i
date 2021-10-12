-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

import ideal.library.graphs.graph;

interface action_context {

  language_settings settings;

  readonly list[action] lookup(type from, action_name name);

  void add(type from, action_name name, action the_action);

  void add_supertype(type subtype, type supertype);

  readonly list[action] resolve(type from, action_name name, origin pos);

  boolean can_promote(action from, type target_type);

  action to_value(action expression, origin the_origin);

  boolean is_subtype_of(abstract_value the_value, type the_type);

  type or null find_supertype_procedure(abstract_value the_value);

  action promote(action from, type target_type, origin pos);

  graph[principal_type, origin] type_graph;
}
