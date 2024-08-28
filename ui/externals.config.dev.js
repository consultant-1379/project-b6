const externals = {
    apps: [{
        path: "app-1",
        entry: "App1"
    }, {
        path: "create-team",
        entry: "CreateTeam"
    }, {
        path: "metrics",
        entry: "Kyzen"
    }, {
        path: "sonar",
        entry: "Sonar"
    }],
    components: {
        default: [],
        shareable: []
    },
    panels: [],
    plugins: []
};

module.exports = externals;