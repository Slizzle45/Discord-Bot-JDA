package slizzle.slizzle45.listeners;

import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivitiesEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.utils.WidgetUtil;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;

import java.util.Objects;

public class EventListener extends ListenerAdapter {

   /* @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getMember().getId().contentEquals("278971678015488001")){
            VoiceChannel myChannel = Objects.requireNonNull(event.getChannelJoined()).asVoiceChannel();
            AudioManager audioManager = myChannel.getGuild().getAudioManager();
            audioManager.openAudioConnection(myChannel);
        }


    }
*/
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;
        String message = event.getMessage().getContentRaw();
        if (message.contains("rise")){
            event.getChannel().sendMessage("Rise oynayan enayidir").queue();
        } else if (message.contentEquals("sa")) {
            event.getChannel().sendMessage("as").queue();
        } else if (message.contains(" bot")) {
            event.getChannel().sendMessage("en iyi bot benim").queue();
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();


        assert user != null;
        String message = user.getAsMention() + " , " + channelMention + "'daki bir mesaja " + emoji + " tepkisi bıraktı!";
        event.getChannel().sendMessage(message).queue();
    }
}
