package com.xynsue;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public final class XynsueConfigScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget moonHexField;
    private TextFieldWidget fireHexField;

    public XynsueConfigScreen(Screen parent) {
        super(Text.literal("Xynsue Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int cx = width / 2;
        int y = 42;

        addDrawableChild(ButtonWidget.builder(moonLabel(), b -> {
            XynsueConfig.moonEnabled = !XynsueConfig.moonEnabled;
            b.setMessage(moonLabel());
            XynsueConfig.save();
        }).dimensions(cx - 105, y, 210, 20).build());
        y += 27;

        moonHexField = new TextFieldWidget(textRenderer, cx - 105, y, 210, 20, Text.literal("Moon HEX"));
        moonHexField.setText(XynsueConfig.moonHex);
        moonHexField.setMaxLength(7);
        addDrawableChild(moonHexField);
        y += 29;

        addDrawableChild(ButtonWidget.builder(fireLabel(), b -> {
            XynsueConfig.fireEnabled = !XynsueConfig.fireEnabled;
            b.setMessage(fireLabel());
            XynsueConfig.save();
        }).dimensions(cx - 105, y, 210, 20).build());
        y += 27;

        fireHexField = new TextFieldWidget(textRenderer, cx - 105, y, 210, 20, Text.literal("Fire HEX"));
        fireHexField.setText(XynsueConfig.fireHex);
        fireHexField.setMaxLength(7);
        addDrawableChild(fireHexField);
        y += 29;

        addDrawableChild(ButtonWidget.builder(throwLabel(), b -> {
            XynsueConfig.pearlThrow = (XynsueConfig.pearlThrow + 10) % 110;
            b.setMessage(throwLabel());
            XynsueConfig.save();
        }).dimensions(cx - 105, y, 210, 20).build());
        y += 27;

        addDrawableChild(ButtonWidget.builder(landingLabel(), b -> {
            XynsueConfig.pearlLanding = (XynsueConfig.pearlLanding + 10) % 110;
            b.setMessage(landingLabel());
            XynsueConfig.save();
        }).dimensions(cx - 105, y, 210, 20).build());
        y += 34;

        addDrawableChild(ButtonWidget.builder(Text.literal("Save"), b -> {
            XynsueConfig.moonHex = XynsueConfig.validHex(moonHexField.getText(), "#7A00FF");
            XynsueConfig.fireHex = XynsueConfig.validHex(fireHexField.getText(), "#7A00FF");
            XynsueConfig.save();
            close();
        }).dimensions(cx - 105, y, 100, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Cancel"), b -> close())
                .dimensions(cx + 5, y, 100, 20).build());
    }

    private Text moonLabel() { return Text.literal("Moon colour: " + (XynsueConfig.moonEnabled ? "ON" : "OFF")); }
    private Text fireLabel() { return Text.literal("Fire colour: " + (XynsueConfig.fireEnabled ? "ON" : "OFF")); }
    private Text throwLabel() { return Text.literal("Pearl throw volume: " + XynsueConfig.pearlThrow + "%  [+10]"); }
    private Text landingLabel() { return Text.literal("Landing / teleport: " + XynsueConfig.pearlLanding + "%  [+10]"); }

    @Override
    public void close() {
        if (moonHexField != null) {
            XynsueConfig.moonHex = XynsueConfig.validHex(moonHexField.getText(), "#7A00FF");
            XynsueConfig.fireHex = XynsueConfig.validHex(fireHexField.getText(), "#7A00FF");
            XynsueConfig.save();
        }
        if (client != null) client.setScreen(parent);
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 18, 0xFFB46CFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
