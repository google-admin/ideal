-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

meta_construct class term_construct {
  implements stringable;

  name_construct the_name;
  name_construct or null variable_name;
  term_repeat repeat;

  override string to_string => utilities.describe(this, the_name);
}
