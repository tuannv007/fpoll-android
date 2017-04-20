package com.framgia.fpoll.ui.pollmanage.history;

import com.framgia.fpoll.R;

/**
 * Created by tuanbg on 3/28/17.
 */
public enum TypeLayoutHistory {
    TYPE_PARTICIPATED(1), TYPE_ALL_PARTICIPANTS_DELETE(2), TYPE_ADD_A_COMMENT(3), TYPE_RESET_LINK(
            4), TYPE_DELETED_COMMENT(5), TYPE_EDIT_VOTE(6), TYPE_EDIT_POLL(7), TYPE_CLOSE_POLL(
            8), TYPE_REOPEN_POLL(9);
    private int mType;

    TypeLayoutHistory(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getResource() {
        switch (this) {
            case TYPE_PARTICIPATED:
                return R.string.title_participant;
            case TYPE_ALL_PARTICIPANTS_DELETE:
                return R.string.title_all_participants_deleted;
            case TYPE_ADD_A_COMMENT:
                return R.string.title_type_Add_a_comment;
            case TYPE_RESET_LINK:
                return R.string.title_reset_link;
            case TYPE_DELETED_COMMENT:
                return R.string.title_delete_comment;
            case TYPE_EDIT_VOTE:
                return R.string.title_edit_vote;
            case TYPE_EDIT_POLL:
                return R.string.title_edit_poll;
            case TYPE_CLOSE_POLL:
                return R.string.title_poll_close;
            case TYPE_REOPEN_POLL:
                return R.string.title_reopen_poll;
            default:
                return R.string.title_participant;
        }
    }

    public int getTextColor() {
        switch (this) {
            case TYPE_PARTICIPATED:
                return R.color.color_blued;
            case TYPE_ALL_PARTICIPANTS_DELETE:
                return R.color.color_red_500;
            case TYPE_ADD_A_COMMENT:
                return R.color.mdtp_accent_color;
            case TYPE_EDIT_POLL:
                return R.color.colorAccent;
            case TYPE_CLOSE_POLL:
                return R.color.color_red_500;
            case TYPE_REOPEN_POLL:
                return R.color.color_blued;
            default:
                return R.color.mdtp_done_text_color;
        }
    }
}
