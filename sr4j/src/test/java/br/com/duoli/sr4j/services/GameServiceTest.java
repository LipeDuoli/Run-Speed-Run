package br.com.duoli.sr4j.services;

import org.junit.Test;

import java.util.List;

import br.com.duoli.sr4j.SpeedRun4jClient;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.games.Game;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.models.levels.Level;
import br.com.duoli.sr4j.models.variables.Variable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GameServiceTest {

    private String gameId = "v1pxjz68";

    @Test
    public void testDescerializeForAllGames_noParameters() {
        List<Game> games = SpeedRun4jClient.getGame().fetch().getData();

        assertNotNull(games);
    }

    @Test
    public void testDescerializeGameWithID_noParameters() {
        Game game = SpeedRun4jClient.getGame().withId(gameId).fetch();

        assertNotNull(game);
        assertEquals(gameId, game.getId());
    }

    @Test
    public void testDescerializeGameCategoriesForId_noParameters() {
        List<Category> categories = SpeedRun4jClient.getGame().withId(gameId).getCategories().fetch();

        assertNotNull(categories);
    }

    @Test
    public void testDescerializeGameLevelsForId_noParameters() {
        List<Level> levels = SpeedRun4jClient.getGame().withId(gameId).getLevels().fetch();

        assertNotNull(levels);
    }

    @Test
    public void testDescerializeGameVariablesForId_noParameters() {
        List<Variable> variables = SpeedRun4jClient.getGame().withId("kyd4pxde").getVariables().fetch();

        assertNotNull(variables);
    }

    @Test
    public void testDescerializeDerivedGamesForId_noParameters() {
        List<Game> games = SpeedRun4jClient.getGame().withId(gameId).getDerivedGames().fetch().getData();

        assertNotNull(games);
    }

    @Test
    public void testDescerializeGameRecordsforId_noParameters() {
        List<Leaderboard> leaderboards = SpeedRun4jClient.getGame().withId(gameId).getGameRecords().fetch().getData();

        assertNotNull(leaderboards);
    }

    @Test
    public void testDescerializeGamesList_bulkSearch() {
        PageableList<Game> games = SpeedRun4jClient.getGame().bulk().fetch();

        assertNotNull(games.getData());
    }

    @Test
    public void testDescerializeGame_embedCategory() {
        Game game = SpeedRun4jClient.getGame().withId(gameId).embedResource(Embed.Games.CATEGORIES).fetch();

        assertNotNull(game.getCategories());
    }

    @Test
    public void testDescerializeGame_embedRegion() {
        Game game = SpeedRun4jClient.getGame().withId(gameId).embedResource(Embed.Games.REGIONS).fetch();

        assertNotNull(game.getRegions());
    }

    @Test
    public void shouldReturnNullWithWrongId(){
        Game game = SpeedRun4jClient.getGame().withId("12311").fetch();

        assertNull(game);
    }

    @Test
    public void testDescerializeGame_embedAll() {
        Game game = SpeedRun4jClient.getGame().withId(gameId).embedResource(Embed.Games.ALL).fetch();

        assertNotNull(game);
    }
}