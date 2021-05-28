import java.text.Collator;
import java.util.Locale;

public enum Nationality {
    British(new Locale("en_UK")),
    Polish(new Locale("pl")),
    SouthAfrican(new Locale("en_ZA")),
    Indian(new Locale("en_IN")),
    German(new Locale("de_DE")),
    Turkish(new Locale("tr")),
    Finnish(new Locale("fi")),
    Estonian(new Locale("et")),
    Danish(new Locale("da")),
    Lithuanian(new Locale("lt"));

    private final Locale _locale;
    private final Collator _collater;

    private Nationality(Locale locale){
        _locale=locale;
        _collater=Collator.getInstance(_locale);
    }

    public Locale locale(){
        return _locale;
    }

    public Collator collator(){
        return _collater;
    }


}
