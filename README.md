# Wild Rift Discord Bot 

A custom Discord bot built using the [JDA](https://github.com/DV8FromTheWorld/JDA) library and powered by [WildStats.gg](https://wildstats.gg/), designed to help Wild Rift players access builds, leaderboards, and player statistics — all from Discord!

## Features

### Builds
- View champion builds with `+build [champion]`
- Example: `+build Ahri` or `+build Miss Fortune`

### Leaderboards
- See top-ranked NA players using `+leaderboards NA`
- Paginated: shows top 10, and page 2 shows ranks 11–50

### Player Stats
- Fetch WildStats.gg player data (level, rank, win rate, KDA, MVPs)
- Parses dynamic script tags with JSoup for live data

### 🧠 Extras
- Uses regex and script parsing for win rate, KDA, and match stats
- Fully event-driven using JDA’s `ListenerAdapter`
- Clean embeds with timestamps and hyperlinks
- Automatically fetches images and champion thumbnails

---

## ⚙️ Technologies Used

| Tech         | Purpose                                  |
|--------------|------------------------------------------|
| **Java**     | Base language                            |
| **JDA**      | Discord bot framework                    |
| **JSoup**    | Web scraping from WildStats.gg           |
| **Regex**    | Parsing embedded data in JS              |

---

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ciddyo/wildrift-bot.git
   cd wildrift-bot
2. Add Your Discord Bot Token
   - Set your Discord bot token as an environment variable:
     ```bash
     export TOKEN=your_token_here
     ```
     - Or hardcode it into the Main.java file (not recommended for production).
3. Compile & Run
   - Use your preferred Java build system (javac, Maven, or Gradle).
   - If using CLI:
     ```bash
     javac -cp .:jda.jar:jsoup.jar Main.java
     java -cp .:jda.jar:jsoup.jar Main
     ```

## Example Commands

Command	Result
+build Ahri	Sends build image + guide link
+build Miss Fortune	Handles multi-word champions
+leaderboards NA	Shows top 10 players + LP needed
+leaderboards NA 2	Displays ranks 11–50

