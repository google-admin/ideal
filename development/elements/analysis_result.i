-- Copyright 2014-2025 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://theideal.org/license/

--- The result of semantic analysis, typically an |action|. 
interface analysis_result {
  extends origin, readonly data;

  action to_action;
}
