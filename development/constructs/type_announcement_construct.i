-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

construct_data class type_announcement_construct {
  readonly list[annotation_construct] annotations;
  kind kind;
  action_name name;

  override string to_string => utilities.describe(this, name);
}
