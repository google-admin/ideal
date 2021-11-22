// Autogenerated from development/scanners/line_comment.i

package ideal.development.scanners;

import ideal.library.elements.*;
import ideal.library.characters.*;
import ideal.library.patterns.*;
import ideal.runtime.elements.*;
import ideal.runtime.characters.*;
import ideal.runtime.patterns.*;
import ideal.runtime.logs.*;
import ideal.machine.characters.*;
import ideal.machine.channels.string_writer;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.notifications.*;
import ideal.development.origins.*;
import ideal.development.comments.*;
import ideal.development.literals.*;
import ideal.development.modifiers.*;
import ideal.development.constructs.constraint_category;
import ideal.development.jumps.jump_category;

import javax.annotation.Nullable;

public class line_comment extends base_scanner_element {
  private final comment_type the_comment_type;
  private final scanner_element start;
  public line_comment(final punctuation_type start_punctuation, final comment_type the_comment_type) {
    this.start = new punctuation_element(start_punctuation);
    this.the_comment_type = the_comment_type;
  }
  public @Override @Nullable scan_state process(final source_content source, final Integer begin) {
    final @Nullable scan_state result = this.start.process(source, begin);
    if (result == null) {
      return null;
    }
    final string input = source.content;
    Integer end;
    for (end = result.end; end < input.size(); end += 1) {
      if (input.get(end) == '\n') {
        end += 1;
        break;
      }
    }
    final string image = input.slice(begin, end);
    final string content = input.slice(result.end, end);
    final origin the_origin = source.make_origin(begin, end);
    final base_token<comment> comment = new base_token<comment>(special_token_type.COMMENT, new comment(this.the_comment_type, content, image), the_origin);
    return new scan_state(((token<Object>) (Object) comment), result.end, end);
  }
}
