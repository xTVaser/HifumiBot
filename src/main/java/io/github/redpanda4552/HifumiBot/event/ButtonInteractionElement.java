/**
 * This file is part of HifumiBot, licensed under the MIT License (MIT)
 * 
 * Copyright (c) 2020 RedPanda4552 (https://github.com/RedPanda4552)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.redpanda4552.HifumiBot.event;

import net.dv8tion.jda.api.interactions.components.Button;

public class ButtonInteractionElement extends AbstractInteractionElement {
    
    public enum ButtonType {
        PRIMARY,
        SECONDARY,
        SUCCESS,
        DANGER
    }
    
    private String label;
    private ButtonType buttonType;
    private Button button;
    
    public ButtonInteractionElement(String userId, String commandName, String label, ButtonType buttonType) {
        super(userId, commandName);
        this.label = label;
        this.buttonType = buttonType;
        
        switch (buttonType) {
        case PRIMARY:
            this.button = Button.primary(uuid.toString(), label);
            break;
        case SECONDARY:
            this.button = Button.secondary(uuid.toString(), label);
            break;
        case SUCCESS:
            this.button = Button.success(uuid.toString(), label);
            break;
        case DANGER:
            this.button = Button.danger(uuid.toString(), label);
            break;
        }
    }
    
    public String getLabel() {
        return label;
    }
    
    public ButtonType getButtonType() {
        return buttonType;
    }
    
    public Button getButton() {
        return button;
    }
}
