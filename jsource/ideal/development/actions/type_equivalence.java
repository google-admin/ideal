/*
 * Copyright 2014-2025 The Ideal Authors. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://theideal.org/license/
 */

package ideal.development.actions;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.types.*;

public class type_equivalence implements equivalence_with_hash<type> {
  public static type_equivalence instance = new type_equivalence();

  @Override
  public Boolean call(type first, type second) {
    return first == second;
  }

  public Integer hash(type the_key) {
    return System.identityHashCode(the_key);
  }
};
