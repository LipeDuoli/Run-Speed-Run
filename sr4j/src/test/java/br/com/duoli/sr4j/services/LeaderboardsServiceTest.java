package br.com.duoli.sr4j.services;

import org.junit.Test;

import br.com.duoli.sr4j.SpeedRun4jClient;
import br.com.duoli.sr4j.exceptions.SearchException;
import br.com.duoli.sr4j.fluent.common.Embed;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LeaderboardsServiceTest {

    private String gameId = "xldev513";
    private String categoryId = "rklg3rdn";

    private String categoryLevelId = "xk9le4k0";
    private String levelId = "rdqz4kdx";

    @Test
    public void testDescerializeLeaderboard_noParameters() {
        Leaderboard leaderboard = SpeedRun4jClient.getLeaderbard()
                .toGame(gameId)
                .toCategory(categoryId).fetch();

        assertNotNull(leaderboard);
        assertEquals(gameId, leaderboard.getGame().getId());
        assertEquals(categoryId, leaderboard.getCategory().getId());
    }

    @Test
    public void testDescerializeLevelLeaderboard_noParameters() {
        Leaderboard leaderboard = SpeedRun4jClient.getLeaderbard()
                .toGame(gameId)
                .toCategory(categoryLevelId)
                .toLevel(levelId).fetch();

        assertNotNull(leaderboard);
        assertEquals(gameId, leaderboard.getGame().getId());
        assertEquals(categoryLevelId, leaderboard.getCategory().getId());
        assertEquals(levelId, leaderboard.getLevel().getId());
    }

    @Test(expected = SearchException.class)
    public void testLeaderbordsSearch_noCategory_throwException() {
        SpeedRun4jClient.getLeaderbard().toGame(gameId).fetch();
    }

    @Test
    public void testDescerializeLeaderboard_embedPlayers() {
        Leaderboard leaderboard = SpeedRun4jClient.getLeaderbard()
                .toGame(gameId)
                .toCategory(categoryId)
                .embedResource(Embed.LeaderBoards.PLAYERS).fetch();

        assertNotNull(leaderboard.getPlayers());
    }

    @Test
    public void testDescerializeLeaderboard_embedAll() {
        Leaderboard leaderboard = SpeedRun4jClient.getLeaderbard()
                .toGame(gameId)
                .toCategory(categoryId)
                .embedResource(Embed.LeaderBoards.ALL).fetch();

        assertNotNull(leaderboard);
    }

}