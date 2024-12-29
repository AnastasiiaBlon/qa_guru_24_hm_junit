package guru.qa.localizationSetUp;

public enum LanguageTranslations {
    RU("Станьте клиентом в мобильном приложении"),
    EN("Become a client in mobile app");

    public final String description;

    LanguageTranslations(String description) {
        this.description = description;
    }
}
