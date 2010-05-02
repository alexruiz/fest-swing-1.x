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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.fest.util.VisibleForTesting;

import static org.fest.util.Closeables.close;
import static org.fest.util.Flushables.flush;

/**
 * Understands creation of mapping files.
 * 
 * @author Alex Ruiz
 */
class CharMappingFileFactory {

  private final List<CharMappingFileCreationListener> fileCreationListeners;
  
  CharMappingFileFactory() {
    fileCreationListeners = new CopyOnWriteArrayList<CharMappingFileCreationListener>();
  }
  
  final void add(CharMappingFileCreationListener l) {
    fileCreationListeners.add(l);
  }
  
  final void remove(CharMappingFileCreationListener l) {
    fileCreationListeners.remove(l);
  }
  
  void createMappingFile(File file, CharMappingTableModel model) throws IOException {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new FileWriter(file));
      int mappingCount = model.rowCount();
      notifyCreationStarted(mappingCount);
      int processed = 0;
      for (int row = 0; row < mappingCount; row++) { 
        writer.println(model.mapping(row));
        notifyMappingsProcessed(++processed);
      }
    } finally {
      flush(writer);
      close(writer);
    }
  }
  
  @VisibleForTesting
  final void notifyCreationStarted(int mappingCount) {
    for (CharMappingFileCreationListener l : fileCreationListeners)
      l.creationStarted(mappingCount);
  }
  
  @VisibleForTesting
  final void notifyMappingsProcessed(int count) {
    for (CharMappingFileCreationListener l : fileCreationListeners)
      l.charMappingsProcessed(count);
  }
}
