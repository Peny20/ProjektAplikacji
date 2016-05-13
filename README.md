# ProjektAplikacji
Projekt aplikacji do interaktywnej rehabilitacji.
W projekcie nie ma zawartych plików muzycznych. Trzeba je dodać ręczenie do folderu raw.
Oraz w klasie Game oraz menu (w metodzie onResume) trzeba wpisać nazwę pliku muzycznego jakiego chcemy aby się otwarzał w grze i menu apolikacji.

protected void onResume()
    {
        super.onResume();
        Muzyka.play(this, R.raw.NAZWAPLIKUMUZYCZNEGO);
    }
