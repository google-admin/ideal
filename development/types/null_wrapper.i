-- Copyright 2014-2025 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://theideal.org/license/

--- Invalid |entity_wrapper|, such as missing from_entity.
-- TODO: use singleton kind here.
class null_wrapper {
  implements entity_wrapper;
  extends debuggable;

  static null_wrapper instance : null_wrapper.new();

  implement type type_bound => common_types.error_type;

  implement string to_string => "<null_wrapper>";
}
