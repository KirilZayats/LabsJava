package co.by;

/**
 * @enumEl CLASSES_NAME_FILE - contains the name of the file that stores the full names
 *         of the exist class 'Food' inheritors  in the specified package
 * @enumEl  WAY_TO_FOOD  - contains the path to the package that stores the class 'Food' inheritors
 */
public enum pathAndFiles {
    CLASSES_NAME_FILE("Classes.txt"),
    WAY_TO_FOOD("co.by.food.");

    private final String path;

    pathAndFiles(String path) {
        this.path=path;
    }
    public String getPath(){
        return path;
    }
}
