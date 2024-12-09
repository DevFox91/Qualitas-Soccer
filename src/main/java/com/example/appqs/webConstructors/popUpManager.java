package com.example.appqs.webConstructors;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class popUpManager {

    public static void closePopupIfOpen() {
        UI currentUI = UI.getCurrent();
        if (currentUI != null && currentUI.getWindows() != null && !currentUI.getWindows().isEmpty()) {
            Window lastWindow = currentUI.getWindows().iterator().next();
            lastWindow.close();
        }
    }
}
