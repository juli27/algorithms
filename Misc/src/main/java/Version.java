/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/**
 * Represents a version number in the format of Semantic Versioning.
 * TODO: fully support the semver standard ? (label varargs, ...)
 *
 * @see <a href="http://semver.org">http://semver.org</a>
 */
public final class Version implements Comparable<Version> {

  public static final Version NULL_VERSION = new Version(0, 0, 0, null);

  private final long version;
  private final String label;
  private String representation;

  private Version(int major, int minor, int patch, String label) {
    long version = major;
    version <<= 16;
    version |= (short) minor;
    version <<= 16;
    version |= (short) patch;

    this.version = version;
    this.label = label;
  }

  /**
   * static factory method.
   *
   * @param major the major version number
   * @param minor the minor version number
   * @param patch the patch version number
   * @return A Version instance representing the version major.minor.patch
   */
  public static Version get(int major, int minor, int patch) {
    return new Version(major, minor, patch, null);
  }

  /**
   * static factory method.
   *
   * @param major the major version number
   * @param minor the minor version number
   * @param patch the patch version number
   * @param label a label
   * @return A Version instance representing the version major.minor.patch-label
   */
  public static Version get(int major, int minor, int patch, String label) {
    return new Version(major, minor, patch, label);
  }

  /**
   * Parses the string argument into a {@link Version} instance. The string has to be in the format
   * "major.minor.patch-label" or "major.minor.patch-label".
   *
   * @param versionString a {@link String} containing the version representation to be parsed
   * @return a instance of the version or NULL_VERSION if an error occurred
   */
  public static Version parse(String versionString) {
    String[] parts = versionString.split("[.-]");

    if (parts.length < 3) {
      return NULL_VERSION;
    }

    short major;
    short minor;
    short patch;

    try {
      major = Short.parseShort(parts[0]);
      minor = Short.parseShort(parts[1]);
      patch = Short.parseShort(parts[2]);
    } catch (NumberFormatException ex) {
      ex.printStackTrace();
      return NULL_VERSION;
    }

    String label = null;

    if (parts.length >= 4) {
      label = parts[3];
    }

    return new Version(major, minor, patch, label);
  }

  @Override
  public int compareTo(Version other) {
    return Long.compare(this.version, other.version);
  }

  @Override
  public int hashCode() {
    int result = Long.hashCode(version);
    result = 31 * result + label.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Version) {
      Version otherVersion = (Version) other;

      return this.version == otherVersion.version && this.label.equals(otherVersion.label);
    }

    return false;
  }

  @Override
  public String toString() {
    if (representation == null) {
      representation = (short) (version >>> 32) + "." + (short) (version >>> 16) + "." + (short) version;

      if (label != null) {
        representation += "-" + label;
      }
    }

    return representation;
  }
}
