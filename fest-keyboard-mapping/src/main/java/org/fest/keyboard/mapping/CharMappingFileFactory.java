/*
 * Created on Apr 9, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import java.io.*;

import static org.fest.util.Closeables.close;
import static org.fest.util.Flushables.flush;
import static org.fest.util.Strings.join;

/**
 * Understands creation of mapping files.
 * 
 * @author Alex Ruiz
 */
class CharMappingFileFactory {

  void createMappingFile(File file, CharMappingTableModel model) throws IOException {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new FileWriter(file));
      int mappingCount = model.rowCount();
      for (int row = 0; row < mappingCount; row++) 
        writer.println(mapping(model, row));
    } finally {
      flush(writer);
      close(writer);
    }
  }
  
  private String mapping(CharMappingTableModel model, int row) {
    String character = model.characterInRow(row);
    if (",".equals(character)) character = "COMMA";
    return join(character, model.keyCodeInRow(row), model.modifierInRow(row)).with(",");
  }
}
