import java.nio.file.NoSuchFileException

fun getResource(resource: String): String {
    return object {}.javaClass.classLoader.getResource(resource)?.toString()?.replace("file:", "")
        ?: throw NoSuchFileException("Unable to find '$resource' in the resources folder")
}
