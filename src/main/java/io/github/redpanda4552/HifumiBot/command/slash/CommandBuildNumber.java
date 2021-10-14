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
package io.github.redpanda4552.HifumiBot.command.slash;

import io.github.redpanda4552.HifumiBot.HifumiBot;
import io.github.redpanda4552.HifumiBot.command.AbstractSlashCommand;
import io.github.redpanda4552.HifumiBot.event.SelectionInteractionElement;
import io.github.redpanda4552.HifumiBot.permissions.PermissionLevel;
import io.github.redpanda4552.HifumiBot.util.Messaging;
import io.github.redpanda4552.HifumiBot.util.SimpleSearch;
import io.github.redpanda4552.HifumiBot.wiki.RegionSet;
import io.github.redpanda4552.HifumiBot.wiki.WikiPage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CommandBuildNumber extends AbstractSlashCommand {

    public CommandBuildNumber() {
        super(PermissionLevel.GUEST);
    }

    @Override
    protected void onExecute(SlashCommandEvent event) {
        OptionMapping option = event.getOption("buildid");
        if (option == null) {
            event.reply("Build ID not provided or invalid!").queue();
            return;
        }
        long buildId = option.getAsLong();
        Optional<String> commitSha = HifumiBot.getSelf().getBuildCommitMap().getCommitSha(buildId);
        commitSha.ifPresentOrElse((sha) -> {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Build Id to Commit");
            eb.addField("Build Id", String.valueOf(buildId), true);
            eb.addField("Commit", String.format("[%s](https://github.com/PCSX2/pcsx2/commit/%s)",
                    StringUtils.abbreviate(sha, 10), sha), true);
            event.replyEmbeds(eb.build()).queue();
        }, () -> {
            event.reply("Could not find that build id in the current development cycle (v1.7)!").queue();
        });
    }

    @Override
    protected CommandData defineSlashCommand() {
        return new CommandData("commitfrombuild", "Determine the git commit a build id is associated with")
                .addOption(OptionType.INTEGER, "buildid", "The build id", true);
    }
}