package slizzle.slizzle45;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import slizzle.slizzle45.commands.CommandManager;
import slizzle.slizzle45.listeners.EventListener;

import javax.security.auth.login.LoginException;

import static net.dv8tion.jda.api.requests.GatewayIntent.ALL_INTENTS;

public class Slizzle45 {

    private final Dotenv config;
    private final ShardManager shardManager;

    public Slizzle45() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");


        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("Slizzle's Orders"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES);
        builder.setMemberCachePolicy(MemberCachePolicy.VOICE);
        builder.setMemberCachePolicy(MemberCachePolicy.OWNER);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS, CacheFlag.VOICE_STATE);
        builder.addEventListeners(new CommandManager());
        shardManager = builder.build();

        // Register listeners
        shardManager.addEventListener(new EventListener());


    }

    public Dotenv getConfig(){
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args){
        try {
            Slizzle45 bot = new Slizzle45();
        } catch (LoginException e) {
            System.out.print("ERROR: Provided bot token is invalid! | Token yanlış");
        }







    }
}
