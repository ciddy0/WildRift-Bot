package Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.temporal.Temporal;
import java.util.Date;

public class Lewd extends ListenerAdapter{
    public static String[][] champs(){
        //2d array of champs and their url lewd image
        String[][] champs = new String[40][2];
        champs[0][0] = "Ahri";
        champs[0][1] = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/96fcf60c-7853-40ce-a47f-10dae409bc73/de8fn4e-cae3beda-8d5a-4241-8492-3a5885cb712f.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzk2ZmNmNjBjLTc4NTMtNDBjZS1hNDdmLTEwZGFlNDA5YmM3M1wvZGU4Zm40ZS1jYWUzYmVkYS04ZDVhLTQyNDEtODQ5Mi0zYTU4ODVjYjcxMmYuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.PesO5nfag50ZwVWQUeisOk0hyXgvXbXDZrpiboEUXhs";

        champs[1][0] = "Orianna";
        champs[1][1] = "https://64.media.tumblr.com/d67155950e90eff3ebe018ce56ebbb87/32f0432de4a61108-15/s1280x1920/757ecf73c06ce728bad8220ff276f4f8d52a1135.jpg";

       champs[2][0] = "Jinx";
       champs[2][1] = "https://i.redd.it/z81r1vc7e7681.jpg";

       champs[3][0] = "Morgana";
       champs[3][1] = "https://64.media.tumblr.com/3fa7819095cd543b7875f6880e1553b0/tumblr_pscv8wbPp11ufm3tmo2_400.jpg";

       champs[4][0] = "lux";
       champs[4][1] = "https://i.redd.it/fsp2496781z71.jpg";

       champs[5][0] = "Ezreal";
       champs[5][1] = "https://us.rule34.xxx//samples/4559/sample_c649b7126872aca68f63ed232ce0b86a.jpg?5193306";

       champs[6][0] = "Akali";
       champs[6][1] = "https://us.rule34.xxx//samples/4660/sample_60b186f8c8d7fb6d15f43574dda158ac.jpg?5311167";

       champs[7][0] = "Katarina";
       champs[7][1] = "https://us.rule34.xxx//images/4058/f903ce1a4ba7a0a72a869d966ce1e090.jpeg?4606730";

       champs[8][0] = "Xayah";
       champs[8][1] = "https://d.wattpad.com/story_parts/227/images/15f71fda90b7aba3946457036884.jpg";

       champs[9][0] = "Sona";
       champs[9][1] = "https://pbs.twimg.com/media/C66fUBYWYAAYcyL.jpg";

       champs[10][0] = "Leona";
       champs[10][1] = "https://cdna.artstation.com/p/assets/images/images/039/302/270/medium/rain-wzq-poolpartyleona1-wm.jpg?1625519991";

       champs[11][0] = "Evelynn";

       champs[11][1] = "https://external-preview.redd.it/l8KmjyMR5fOQHj2NJClKqOTGHY-2h1sRE1hx9oW1WAc.jpg?auto=webp&s=cb85b023ec07499b15eb0494dfd7881108bc24b0";

       champs[12][0] = "Caitlyn";
       champs[12][1] = "https://pbs.twimg.com/media/D0vik4SWwAEeYZn.jpg";

       champs[13][0] = "Slyas";
       champs[13][1] = "https://pbs.twimg.com/media/EjjmmF_XkAA6lOa.jpg";

       champs[14][0] = "Ashe";
       champs[14][1] = "https://cdn.anime-pictures.net/previews/a07/a0733d88e6f74856c8b7831ab239fbfa_bp.png.webp";

       champs[15][0] = "Kaisa";
       champs[15][1] = "https://cdna.artstation.com/p/assets/images/images/017/933/712/medium/windwalker-ture-kda-kaisa03.jpg?1557904182";

       champs[16][0] = "MF";
       champs[16][1] = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/0f1b12da-674c-469d-8811-3e00db5e19a1/da8qvr2-8e71ec97-9559-4556-b804-514d03ec3bfe.jpg/v1/fill/w_1024,h_1536,q_75,strp/pool_party_miss_fortune_by_joacoful_da8qvr2-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTUzNiIsInBhdGgiOiJcL2ZcLzBmMWIxMmRhLTY3NGMtNDY5ZC04ODExLTNlMDBkYjVlMTlhMVwvZGE4cXZyMi04ZTcxZWM5Ny05NTU5LTQ1NTYtYjgwNC01MTRkMDNlYzNiZmUuanBnIiwid2lkdGgiOiI8PTEwMjQifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.s61uWpR9WiXyJdoZa82dCeeJMGoOsXiBfKoLnM9qbdk";

       champs[17][0] = "Irelia";
       champs[17][1] = "http://lol-stats.net/uploads/qqe4oS9i8G1b3WvZpOc9glwDcp0Hhnn4AJYjWTVO.jpeg";

       champs[18][0] = "Diana";
       champs[18][1] = "https://pbs.twimg.com/media/EffOJn9VoAE7k2S?format=jpg&name=large";

       champs[19][0] = "Janna";
       champs[19][1] = "https://cdn.donmai.us/original/3f/d2/__janna_league_of_legends_drawn_by_instant_ip__3fd2d3fddb625a5f7f6959120e77825d.jpg";

       champs[20][0] = "Kayle";
       champs[20][1] = "https://cdna.artstation.com/p/assets/images/images/033/846/226/medium/are-jay-peralta-transcended-kayle-bikini-v2-for-posting.jpg?1627444682";

       champs[21][0] = "Nami";
       champs[21][1]= "https://external-preview.redd.it/0rSFt7segmqvMprxniPAIMJcdfZVSZdVDE1CBOvdKc4.png?auto=webp&s=6d011e2f4aaa708ae5ff456547f5d3e17380738f";

       champs[22][0] = "Riven";
       champs[22][1] = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/9ad8fbc8-aee8-4e92-a04b-6abf7a036a51/dczxr2n-52fb71f8-d9d4-4aa3-8cf5-a3b56a92e8b8.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzlhZDhmYmM4LWFlZTgtNGU5Mi1hMDRiLTZhYmY3YTAzNmE1MVwvZGN6eHIybi01MmZiNzFmOC1kOWQ0LTRhYTMtOGNmNS1hM2I1NmE5MmU4YjguanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.DKyQ7s4rTvV4k-KMKo1-VY_AHB5m00-dbqOWzxklogk";

       champs[23][0] = "Annie";
       champs[23][1] = "https://c.tenor.com/M9ZtB5i2lL0AAAAd/ladies-and-gentlemen-we-got-him.gif";

       champs[24][0] = "khazix";
       champs[24][1] = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/2e6c41c7-0f38-4558-a571-0b36a1aa7ed6/d5ovkl0-e4d0fc75-4376-47a4-b614-3cad25ae7fd2.jpg/v1/fill/w_1280,h_1463,q_75,strp/khazix_by_dw628_d5ovkl0-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTQ2MyIsInBhdGgiOiJcL2ZcLzJlNmM0MWM3LTBmMzgtNDU1OC1hNTcxLTBiMzZhMWFhN2VkNlwvZDVvdmtsMC1lNGQwZmM3NS00Mzc2LTQ3YTQtYjYxNC0zY2FkMjVhZTdmZDIuanBnIiwid2lkdGgiOiI8PTEyODAifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.PeDvbLxCloquetnUvIjJ2_CCQ_59gUtz13MLCUXdTqs";

       champs[25][0] = "Rammus";
       champs[25][1] = "https://preview.redd.it/xnni13voshq61.jpg?width=2048&format=pjpg&auto=webp&s=3e789974eac502223d44079e5f27c2d8b5d4ae19";

       champs[26][0] = "Rengar";
       champs[26][1] = "http://i.4pcdn.org/tg/1402790080507.png";

       champs[27][0] = "Braum";
       champs[27][1] = "https://leagueofbarahome.files.wordpress.com/2018/01/tumblr_ot1b0t6hmu1v4g213o1_1280.jpg?w=1400";

       champs[28][0] = "Graves";
       champs[28][1] = "https://pbs.twimg.com/media/EuPA1HBVoAIQIIA?format=jpg&name=4096x4096";

       champs[29][0] = "TF";
       champs[29][1] = "https://i.imgur.com/btcTpPx.jpg";

       champs[30][0] = "Lee";
       champs[30][1] = "https://pbs.twimg.com/media/CFl-unAW0AIigyB.jpg";

       champs[31][0] = "Senna";
       champs[31][1] = "https://art.ngfiles.com/images/1878000/1878556_honeyshaman_for-senna.png?f1623358304";

       champs[32][0] = "Pantheon";
       champs[32][1] = "https://pbs.twimg.com/media/EidU7ALWsAElmnt.jpg";

       champs[33][0]= "Draven";
       champs[33][1] = "https://i.imgur.com/qxtpiuF.jpg";
       return champs;



    }
    public void onMessageReceived(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        //Guild guild = event.getGuild();
        String imageurl = "";
        String champ = "";
        if (args[0].equalsIgnoreCase(wildRiftBot.prefix + "lewd")) {
            //creates an array of champs
            String[][] champs = champs();

            //iterates through the array of champs
            try {
            for (int i = 0; i < 40; i++) {
                if (champs[i][0].equalsIgnoreCase(args[1])) {
                    //sets the image url
                    imageurl = champs[i][1];
                    champ = champs[i][0];
                    break;
                }
            }
                //creates a new embed
                EmbedBuilder lewd = new EmbedBuilder();
                //get users name
                String user = "Viewed by: "+event.getMember().getUser().getName();

                //details to embed
                lewd.setFooter("xoxo", event.getMember().getUser().getAvatarUrl());
                lewd.setColor(0xCBC3E3);
                lewd.setImage(imageurl);
                lewd.setTitle(champ);
                lewd.setTimestamp(new Date().toInstant());
                //sends the embed
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessageEmbeds(lewd.build()).queue();
                //clears
                lewd.clear();
            }catch (Exception e){
                //throws exception if champion is not in code
                event.getChannel().sendTyping().queue();

                //creates embed for info
                EmbedBuilder lewdInfo = new EmbedBuilder();
                lewdInfo.setFooter("Info",event.getMember().getUser().getAvatarUrl());
                lewdInfo.setColor(0xCBC3e3);
                lewdInfo.setTitle("Lewd Info");
                lewdInfo.setDescription("Still working on adding more champs!\n If you would like a champ added dm me");

                //sends embed
                event.getChannel().sendMessageEmbeds(lewdInfo.build()).queue();
                //clears
                lewdInfo.clear();

            }
        }

    }

}
