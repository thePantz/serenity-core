package net.serenitybdd.reports.configuration

import net.thucydides.core.ThucydidesSystemProperty
import net.thucydides.core.util.EnvironmentVariables
import java.nio.file.Path
import java.nio.file.Paths

class PathReportProperty(val property: ThucydidesSystemProperty, val defaultValue: String) : ReportProperty<Path> {
    override fun configuredIn(environmentVariables: EnvironmentVariables) : Path {
        return Paths.get(environmentVariables.getProperty(property, defaultValue))
    }
}