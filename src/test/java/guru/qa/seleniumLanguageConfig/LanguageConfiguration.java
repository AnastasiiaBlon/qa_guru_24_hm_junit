package guru.qa.seleniumLanguageConfig;

public enum LanguageConfiguration {
    日本語("日本語"),
    中文简体("中文简体");

    public final String description;

    LanguageConfiguration(String description) {
        this.description = description;
    }
}
