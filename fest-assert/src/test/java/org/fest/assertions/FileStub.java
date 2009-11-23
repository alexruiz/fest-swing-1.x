/*
 * Created on Feb 9, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import java.io.File;

/**
 * Understands a <code>{@link File}</code> stub.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class FileStub extends File {
  private static final long serialVersionUID = 1L;

  private boolean absolute;
  private String absolutePath;
  private boolean directory;
  private boolean exists;
  private boolean file;
  private long length;

  public static FileStub newFile(String absolutePath) {
    return new FileStub(absolutePath);
  }

  private FileStub(String absolutePath) {
    super("");
    withAbsolutePath(absolutePath);
  }

  FileStub ensureIsFile() {
    file = true;
    return this;
  }

  FileStub ensureIsDirectory() {
    directory = true;
    return this;
  }

  FileStub ensureExists() {
    exists = true;
    return this;
  }

  FileStub ensureIsAbsolute() {
    absolute = true;
    return this;
  }

  FileStub ensureIsRelative() {
    absolute = false;
    return this;
  }

  FileStub withAbsolutePath(String newAbsolutePath) {
    absolutePath = newAbsolutePath;
    return this;
  }

  @Override public boolean exists() {
    return exists;
  }

  @Override public String getAbsolutePath() {
    return absolutePath;
  }

  @Override public String getPath() {
    return absolutePath;
  }

  @Override public boolean isAbsolute() {
    return absolute;
  }

  @Override public boolean isDirectory() {
    return directory;
  }

  @Override public boolean isFile() {
    return file;
  }

  @Override public long length() {
    return length;
  }

  void length(long val) { length = val; }

  /** @see java.lang.Object#hashCode() */
  @Override public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((absolutePath == null) ? 0 : absolutePath.hashCode());
    return result;
  }

  /** @see java.lang.Object#equals(java.lang.Object) */
  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (!(obj instanceof FileStub)) return false;
    final FileStub other = (FileStub) obj;
    if (absolutePath == null) {
      if (other.absolutePath != null) return false;
    } else if (!absolutePath.equals(other.absolutePath)) return false;
    return true;
  }
}
