/**
 * Represents a version number in the format of http://semver.org.
 */
public final class Version implements Comparable<Version> {

  public static final Version NULL_VERSION = new Version(0, 0, 0, null);
  private final int version;
  private String label;
  private String representation;

  private Version(int major, int minor, int patch, String label) {
    int version = 0;
    version = version | (major << 16);
    version = version | (minor << 8);
    version = version | patch;

    this.version = version;
    this.label = label;
  }

  /**
   * static factory method.
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
   * @param major the major version number
   * @param minor the minor version number
   * @param patch the patch version number
   * @param label
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

    byte major;
    byte minor;
    byte patch;

    try {
      major = Byte.parseByte(parts[0]);
      minor = Byte.parseByte(parts[1]);
      patch = Byte.parseByte(parts[2]);
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
    return Integer.compare(this.version, other.version);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(version);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Version) {
      Version otherVersion = (Version) other;

      return this.version == otherVersion.version;
    }

    return false;
  }

  @Override
  public String toString() {
    if (representation == null) {
      representation = (byte)(version >>> 16) + "." + (byte)(version >>> 8) + "." + (byte)version;

      if (label != null) {
        representation += "-" + label;
      }
    }

    return representation;
  }
}
