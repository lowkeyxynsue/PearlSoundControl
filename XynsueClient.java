package com.xynsue;

import net.fabricmc.api.ClientModInitializer;

public final class XynsueClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        XynsueConfig.load();
    }
}
