package br.com.duoli.sr4j.services;

import org.junit.Test;

import br.com.duoli.sr4j.SpeedRun4jClient;
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
        assertEquals(gameId, leaderboard.getGame());
        assertEquals(categoryId, leaderboard.getCategory());
    }

    @Test
    public void testDescerializeLevelLeaderboard_noParameters() {
        Leaderboard leaderboard = SpeedRun4jClient.getLeaderbard()
                .toGame(gameId)
                .toCategory(categoryLevelId)
                .toLevel(levelId).fetch();

        assertNotNull(leaderboard);
        assertEquals(gameId, leaderboard.getGame());
        assertEquals(categoryLevelId, leaderboard.getCategory());
        assertEquals(levelId, leaderboard.getLevel());
    }
}