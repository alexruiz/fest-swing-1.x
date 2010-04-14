/*
 * Created on Apr 14, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;


import java.io.*;
import java.util.*;

import org.junit.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.keyboard.mapping.CharMapping.newCharMapping;
import static org.fest.util.Arrays.array;
import static org.fest.util.Closeables.close;
import static org.fest.util.Files.newTemporaryFile;

/**
 * Tests for <code>{@link CharMappingFileFactory#createMappingFile(java.io.File, CharMappingTableModel)}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMappingFileFactory_createMappingFile_Test {

  private static CharMappingFileFactory factory;
  
  private File mappingFile;
  private ModelStub model;
  
  @BeforeClass
  public static void setUpOnce() {
    factory = new CharMappingFileFactory();
  }
  
  @Before
  public void setUp() {
    mappingFile = newTemporaryFile();
    model = new ModelStub();
    model.add("a", "A", "NO_MASK");
    model.add("S", "S", "SHIFT_MASK");
  }
  
  @After
  public void tearDown() {
    if (mappingFile != null) mappingFile.delete();
  }
  
  @Test
  public void should_write_mapping_file() throws IOException {
    factory.createMappingFile(mappingFile, model);
    List<String> contents = mappingFileContents();
    assertThat(contents).containsOnly("a, A, NO_MASK", "S, S, SHIFT_MASK");
  }
  
  private List<String> mappingFileContents() throws IOException {
    List<String> contents = new ArrayList<String>();
    BufferedReader input = null;
    try {
      input = new BufferedReader(new FileReader(mappingFile));
      String line = null;
      while ((line = input.readLine()) != null) {
        contents.add(line);
      }
    } finally {
      close(input);
    }
    return contents;
  }
  
  private static class ModelStub implements CharMappingTableModel {
    private final List<String[]> data = new ArrayList<String[]>(); 
    
    void add(String character, String keyCode, String modifier) {
      data.add(array(character, keyCode, modifier));
    }
    
    public int rowCount() {
      return data.size();
    }

    public CharMapping mapping(int row) {
      String[] rowData = data.get(row);
      return newCharMapping(rowData[0], rowData[1], rowData[2]);
    }
  }
}
