package ru.dekabrsky.vkApi;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.enums.GroupsSort;
import com.vk.api.sdk.objects.groups.Fields;
import com.vk.api.sdk.objects.groups.Group;

public class VkRepository {
    private final int APP_ID =
            0;
    private final String CODE =
            "";
    private final VkApiClient vk;
    private final UserActor actor;

    public VkRepository() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public Group getMostPopularGroupByPlayer(String player) throws ClientException, ApiException {
        return vk.groups().search(actor, player)
                .sort(GroupsSort.ATTENDANCE)
                .count(1)
                .execute()
                .getItems()
                .get(0);
    }

    public int getGroupMembersCount(Group miniGroup) throws ClientException, ApiException {
        return vk.groups().getByIdLegacy(actor)
                .groupId(miniGroup.getId().toString())
                .fields(Fields.MEMBERS_COUNT)
                .execute()
                .get(0)
                .getMembersCount();
    }
}
