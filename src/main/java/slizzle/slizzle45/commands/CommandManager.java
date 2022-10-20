package slizzle.slizzle45.commands;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.managers.AudioManager;
import slizzle.slizzle45.lavaplayer.PlayerManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getGuild().upsertCommand("play", "Şarkı play")
                .addOption(OptionType.STRING, "sarki", "Şarkı adını veya linkini gir",true, true)
                .queue();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("play") || event.getName().equals("çal")) {

            if (!event.getInteraction().getMember().getVoiceState().inAudioChannel()) {
                event.getInteraction().getChannel().asTextChannel().sendMessage("Bu komutu playıştırabilmek için bir sesli kanalda olmalısınız").queue();
                return;
            }


            String link = event.getOption("sarki").toString();
            if (!event.getGuild().getSelfMember().getVoiceState().inAudioChannel()) {
                final AudioManager audioManager = event.getGuild().getAudioManager();
                final VoiceChannel memberChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

                audioManager.openAudioConnection(memberChannel);



                if (!isUrl(link)) {
                    link = "ytsearch:" + link + "audio";
                }

            }

            PlayerManager.getINSTANCE().loadAndPlay(event.getChannel().asTextChannel(), link);


            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("Emir yerine getiriliyor").setEphemeral(true).queue();
        }


    }

    public boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }catch (URISyntaxException e){
            return false;
        }
    }

}
