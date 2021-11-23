// Autogenerated from development/comments/comment_type.i

package ideal.development.comments;

import ideal.library.elements.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;

public enum comment_type implements deeply_immutable_data, reference_equality {
  NEWLINE(false),
  WHITESPACE(false),
  LINE_COMMENT(false),
  BLOCK_COMMENT(false),
  LINE_DOC_COMMENT(true),
  BLOCK_DOC_COMMENT(true);
  public final boolean is_doc;
  private comment_type(final boolean is_doc) {
    this.is_doc = is_doc;
  }
  public string to_string() {
    return new base_string(toString());
  }
}
