/*
 * Copyright 2014-2025 The Ideal Authors. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://theideal.org/license/
 */

package ideal.development.extensions;

import ideal.library.elements.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.runtime.texts.*;
import ideal.development.elements.*;
import ideal.development.components.*;
import ideal.development.names.*;
import ideal.development.actions.*;
import ideal.development.constructs.*;
import ideal.development.values.*;
import ideal.development.analyzers.*;

public class please_construct extends extension_construct {
  public final construct the_statement;

  public please_construct(construct the_statement, origin pos) {
    super(pos);
    this.the_statement = the_statement;
  }

  @Override
  public readonly_list<construct> children() {
    return new base_list<construct>(the_statement);
  }

  @Override
  public analyzable to_analyzable() {
    return new grouping_analyzer(base_analyzer.make(the_statement), this);
  }

  @Override
  public text_fragment print(printer p) {
    list<text_fragment> fragments = new base_list<text_fragment>();

    fragments.append(p.print_word(keywords.PLEASE));
    fragments.append(p.print_space());
    fragments.append(p.print(the_statement));

    return text_utilities.join(fragments);
  }

  @Override
  public boolean is_terminated() {
    // This may not be right.
    return true;
  }

  @Override
  public construct transform(transformer the_transformer) {
    return new please_construct(the_transformer.transform(the_statement), this);
  }

  @Override
  public string to_string() {
    return utilities.describe(this, the_statement.to_string());
  }
}
